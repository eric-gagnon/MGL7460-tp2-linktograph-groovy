package eric_gagnon.com.github.linktograph

import eric_gagnon.com.github.linktograph.Link

import java.nio.file.*

class App {

    def rootDir

    def App() {
        rootDir = ".."
    }

    void doProcess() {
        println "doProcess"

        def inputFilePath = Paths.get(rootDir, "input/clean-links.txt")

        def sourceLinks = subProcessGetUniqueLinks(inputFilePath.toString())

        subProcessScrapFileToCache(sourceLinks)
        subProcessTikaTika()
        subProcessExtractWords()
        subProcessPrepareDataForGraph()
        subProcessExportGraph()
    }

    def subProcessGetUniqueLinks(filePath) {
        println "subProcessGetUniqueLinks"
        println "inputFilePath: $filePath"

        return Link.getUniqueLinksFromFile(filePath)
    }

    def subProcessScrapFileToCache(sourceLinks) {
        println "subProcessScrapFileToCache"
        println "sourceLinks: $sourceLinks"

        def cacheFolder = Paths.get(rootDir, "cache", "web")

        Scraper.ScrapFilesToCache(sourceLinks, cacheFolder.toString())
    }

    def subProcessTikaTika() {
        println "subProcessTikaTika"

        /*def cacheFolderPath = Paths.get("cache","tika")*/

    }

    def subProcessExtractWords() {
        println "subProcessExtractWords"
    }

    def subProcessPrepareDataForGraph() {
        println "subProcessPrepareDataForGraph"
    }

    def subProcessExportGraph() {
        println "subProcessExportGraph"
    }

}
