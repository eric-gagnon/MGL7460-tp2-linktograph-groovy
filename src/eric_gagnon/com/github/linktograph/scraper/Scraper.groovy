package eric_gagnon.com.github.linktograph

import eric_gagnon.com.github.linktograph.Link
import java.nio.file.*

class Scraper {

    static def ScrapeFilesToCache(sourceLinks, cacheFolderPath) {

        def threads = []

        // todo_eg : add this verification everywhere?
        if (sourceLinks instanceof Collection) {
            // Concurrent Download.
            sourceLinks.each { link ->
                def aThread = new Thread({
                    def cacheFilePath = getCacheFilePath(link, cacheFolderPath)

                    if (!new File(cacheFilePath).exists()) {
                        downloadFileForLink(link, cacheFilePath)
                    } else {
                        println "Skip downloadFileForLink, file already in cache : $link"
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

    static def downloadFileForLink(link, cacheFilePath) {
        try {
            println "downloadFileForLink: $link"
            println "cacheFilePath: $cacheFilePath"
            /* todo_eg_bug, le lien
               https://invisu.inha.fr/fr/formations/lundis-numeriques/lundis-numeriques-annee-2015-2016/florence-clavaud-un-projet-de-prototype-pour-la-visualisation-en-graphe-de-metadonnees-archivistiques.html
               ne semble par réagir et bloque le traitement.
            */
            // todo_eg : groovy already have a cache? useCaches : set the use cache property for the URL connection
            new File(cacheFilePath) << new URL(link).getBytes([connectTimeout: 30000, readTimeout: 30000, useCaches: true, requestProperties: ['User-agent': 'Mozilla/5.0']] /* todo_eg : a revoir + version Go */)
        } catch (e) {
            println "Unknown error while trying to download link: $link"
            // Add more data for investigation.
            println(e.toString());
            println(e.getMessage());
            println(e.getStackTrace());
        }
    }

    // todo_eg: move this in common.
    static def getCacheFilePath(link, cacheFolderPath) {
        def filename = Link.getSha1FileNameForLink(link)
        // todo_eg : concurrent download with Groovy...
        def cacheFilePath = Paths.get(cacheFolderPath, filename).toString()

        return cacheFilePath
    }
}
