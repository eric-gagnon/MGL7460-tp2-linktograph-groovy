package eric_gagnon.com.github.linktograph

import eric_gagnon.com.github.linktograph.Link
import java.nio.file.*

class Scraper {

    static def ScrapFilesToCache(sourceLinks, cacheFolderPath) {
        // todo_eg : add this verification everywhere?

        if (sourceLinks instanceof Collection) {
            // Concurrent Download.
            sourceLinks.each {
                // todo_eg : concurrent download with Groovy...
                def cacheFilePath = getCacheFilePath(it, cacheFolderPath)

                if (!new File(cacheFilePath).exists()) {
                    downloadFileForLink(it, cacheFilePath)
                } else {
                    println "Skip downloadFileForLink, file already in cache : $it "
                }
            }
        } else {
            println "Skipping. SourceLinks is not a collection : $sourceLinks."
        }
    }

    static def downloadFileForLink(link, cacheFilePath) {
        try {
            println "downloadFileForLink: $link"
            println "cacheFilePath: $cacheFilePath"
            /* todo_eg_bug, le lien https://invisu.inha.fr/fr/formations/lundis-numeriques/lundis-numeriques-annee-2015-2016/florence-clavaud-un-projet-de-prototype-pour-la-visualisation-en-graphe-de-metadonnees-archivistiques.html
              ne semble par réagir et bloque le traitement.
            */
            // todo_eg : groovy already have a cache? useCaches : set the use cache property for the URL connection
            new File(cacheFilePath) << new URL(link).getBytes([connectTimeout: 2000, readTimeout: 3000, useCaches: true] /* todo_eg : a revoir + version Go */)
        } catch (e) {
            println "Unknown error while trying to download link: $link"
            // Add more data for investigation.
            println(e.toString());
            println(e.getMessage());
            println(e.getStackTrace());
        }
    }

    static def getCacheFilePath(link, cacheFolderPath) {
        def filename = Link.getSha1FileNameForLink(link)
        // todo_eg : concurrent download with Groovy...
        def cacheFilePath = Paths.get(cacheFolderPath, filename).toString()

        return cacheFilePath
    }


}
