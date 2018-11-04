package eric_gagnon.com.github.linktograph

import eric_gagnon.com.github.linktograph.link.Link

import java.nio.file.*

class App {

    void doProcess() {
        println "doProcess"

        // todo_eg : Pourquoi "." = src?
        def rootDir = new File("..")
        def inputFilePath = Paths.get(rootDir.path, "input/clean-links.txt")

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

        def link = new Link()
        def sourceLinks = link.getUniqueLinksFromFile(filePath)

        return sourceLinks
    }

    def subProcessScrapFileToCache(sourceLinks) {
        println "subProcessScrapFileToCache"
        println "sourceLinks: $sourceLinks"
    }

    def subProcessTikaTika() {
        println "subProcessTikaTika"
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
