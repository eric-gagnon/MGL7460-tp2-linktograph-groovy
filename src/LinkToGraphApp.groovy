class LinkToGraphApp {

    void doProcess() {
        println "doProcess"

        subProcessGetUniqueLinks()
        subProcessScrapFileToCache()
        subProcessTikaTika()
        subProcessExtractWords()
        subProcessPrepareDataForGraph()
        subProcessExportGraph()
    }

    def subProcessGetUniqueLinks(){
        println "subProcessGetUniqueLinks"
    }

    def subProcessScrapFileToCache(){
        println "subProcessScrapFileToCache"
    }

    def subProcessTikaTika(){
        println "subProcessTikaTika"
    }

    def subProcessExtractWords() {
        println "subProcessExtractWords"
    }

    def subProcessPrepareDataForGraph(){
        println "subProcessPrepareDataForGraph"
    }

    def subProcessExportGraph(){
        println "subProcessExportGraph"
    }

}
