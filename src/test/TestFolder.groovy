package test

class TestFolder {

    static def getTempTestFolder(testIdentifier) {
        File temp = File.createTempDir("linktograph-", "-$testIdentifier-test")
        // todo_eg in ScrapFilesToCache if downloadTestUrl is not a list.
        println "Temporary test folder created in : $temp"

        return temp
    }
}


