package eric_gagnon.com.github.linktograph.extractor

import eric_gagnon.com.github.linktograph.Link
import eric_gagnon.com.github.linktograph.Scraper

import java.nio.file.Paths

// Start tika local :
// todo_eg : documentation in README.MD
// cd  cd /c/Users/****/Desktop/tp2
// java -classpath /c/Users/****/Desktop/tp2/tika-ner-resources/:/c/Users/****/Desktop/tp2/tika-server-1.19.1.jar org.apache.tika.server.TikaServerCli --host=* --port=9998 --config=tika-config.xml

class TikaExtractor {
    static def RMETA_AS_TEXT_REQUEST = "http://localhost:9998/rmeta/text"

    static def getAllTikaResultsToCache(sourceLinks, sourceCacheFolderPath, tikaCacheFolderPath) {
        concurrentProcess(sourceLinks, sourceCacheFolderPath, tikaCacheFolderPath, this.&getTikaRMetaAndTextResponseToCache)
    }

    // todo_eg : duplication. Call process with rest params?
    static def concurrentProcess(sourceLinks, sourceCacheFolderPath, tikaCacheFolderPath, process) {

        def threads = []

        // todo_eg : add this verification everywhere?
        if (sourceLinks instanceof Collection) {
            // Concurrent Download.
            sourceLinks.each { link ->
                def aThread = new Thread({
                    def cacheFilePath = Scraper.getCacheFilePath(link, tikaCacheFolderPath)

                    if (!new File(cacheFilePath).exists()) {
                        /* todo_eg : need to recalculate sha1, is this ok? */
                        def sha1Identifier = Link.getSha1FileNameForLink(link)
                        process(link, sha1Identifier, sourceCacheFolderPath, tikaCacheFolderPath)
                    } else {
                        println "Skip tika processing, file response already in cache : $link"
                    }
                })

                threads << aThread
            }

            threads.each { it.start() }
            threads.each { it.join() }

        } else {
            println "Skipping. SourceLinks is not a collection : $sourceLinks."
        }
    }

    static def getTikaRMetaAndTextResponseToCache(link, send_to_tika_filename, send_to_tika_file_folderPath, save_result_cache_folderPath) {
        // todo_eg: tika cannot parse pdf, why?  /*"application/octet-stream" "application/pdf" */
        getTikaRMetaAndTextResponseToCacheWithMimeType(link, send_to_tika_filename, send_to_tika_file_folderPath, save_result_cache_folderPath, "content/unknown")
    }

    static def getTikaRMetaAndTextResponseToCacheWithMimeType(link, send_to_tika_filename, send_to_tika_file_folderPath, save_result_cache_folderPath, mimeType) {

        def send_to_tika_filepath = Paths.get(send_to_tika_file_folderPath, send_to_tika_filename).toString()

        // It's possible that the file failed to download at previous step, we need to check if the file exist first.
        if (new File(send_to_tika_filepath).exists()) {
            def connection = new URL(RMETA_AS_TEXT_REQUEST).openConnection();

            connection.setRequestMethod("PUT")
            connection.setDoOutput(true)

            connection.setRequestProperty("Accept", "application/json")
            // connection.setRequestProperty("Content-Type", mimeType)
            def file = new File(send_to_tika_filepath)
            def bytes = file.getBytes()
            connection.getOutputStream().write(bytes);

            def responseCode = connection.getResponseCode();

            if(responseCode.equals(200)) {
                // Save to file.
                def cacheFilePath = Paths.get(save_result_cache_folderPath, send_to_tika_filename /* save using same sha1 identifier as send_to_tika_filename. */).toString()
                new File(cacheFilePath) << connection.getInputStream().getBytes()
            } else {
                println "getTikaRMetaAndTextResponseToCache : there was an error ($responseCode) while processing the file :  $link"
                println " - check file: $send_to_tika_filepath"
            }
        } else {
            println "Skip tika processing, source file missing from cache (maybe a failed download) : $link"
        }
    }
}
