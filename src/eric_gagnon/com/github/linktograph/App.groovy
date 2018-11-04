package eric_gagnon.com.github.linktograph

class App {

    void doProcess() {
        println "doProcess"

        subProcessGetUniqueLinks()
        subProcessScrapFileToCache()
        subProcessTikaTika()
        subProcessExtractWords()
        subProcessPrepareDataForGraph()
        subProcessExportGraph()
    }

    def subProcessGetUniqueLinks() {
        println "subProcessGetUniqueLinks"

        // todo_eg : Pourquoi "." = src?
        def currentDir = new File(".").absolutePath
        println currentDir
    }

    def subProcessScrapFileToCache() {
        println "subProcessScrapFileToCache"
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
