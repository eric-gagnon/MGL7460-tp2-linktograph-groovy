package eric_gagnon.com.github.linktograph.link

class Link {

    def getUniqueLinksFromFile(filePath) {

        def links = getLinksFromFile(filePath)
        return removeDuplicates(links)
    }

    private def getLinksFromFile(filePath) {

        def links = []

        try {
            new File(filePath).eachLine('UTF-8') {
                // Vérifier si valide.
                links.add(it)
            }
        }
        catch (FileNotFoundException e) {
            println "The file was not found: $filePath."
        }
        catch (Exception e) {
            println "Unknown exception while trying to read file: ($filePath)."
            /* Get maximum information for debug, this is unattended nor managed error. */
            println(e.toString());
            println(e.getMessage());
            println(e.getStackTrace());
        }

        def linkSize = links.size()
        println "Number of links found in file: $linkSize"

        return links
    }

    /* todo_eg : This function could be removed entirely, removing duplicates is trivial in Groovy compared to Go. */
    def removeDuplicates(links) {
        // todo_eg : what's the prefered way in Groovy?
        links = links.unique(false /* return a new list, keep orignal links unmodifed. */)
        def linksSize = links.size()
        println "Number of links without duplicates: $linksSize"

        return links
    }

    def getSha1FileNameForLink(link) {
        def sha1 = link.digest('SHA-1')
        println "sha1 $link, $sha1"
        return sha1
    }
}
