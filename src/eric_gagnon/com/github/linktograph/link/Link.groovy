package eric_gagnon.com.github.linktograph

class Link {

    static def getUniqueLinksFromFile(filePath) {

        def links = getLinksFromFile(filePath)
        return removeDuplicates(links)
    }

    static def isLinkValidURL(link) {
        /* There is no default implementation like Go but new URL will return a error if the URL is not parsed correctly. */
        try {
            new URL(link)
            return true
        } catch (Exception e) {
            println "The link was not parsed as a valid URL, it will be ignored : $link"
            return false
        }
    }

    static def getLinksFromFile(filePath) {

        def links = []

        try {
            new File(filePath).eachLine('UTF-8') {
                // Vérifier si valide.
                if (isLinkValidURL(it)) {
                    links.add(it)
                }
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

    static def removeDuplicates(links) {

        links = links.unique(false /* return a new list, keep orignal links unmodifed. */)
        def linksSize = links.size()
        println "Number of links without duplicates: $linksSize"

        return links
    }

    static def getSha1FileNameForLink(link) {
        def sha1 = link.digest('SHA-1')
        return sha1
    }
}
