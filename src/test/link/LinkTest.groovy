package test

import eric_gagnon.com.github.linktograph.Link

class linkTest extends GroovyTestCase {

    void testGetSha1FileNameForLink() {
        println("test GetSha1FileNameForLink called.")

        // Manually verified with : http://www.sha1-online.com/
        def sha1filename = Link.getSha1FileNameForLink("https://github.com/eric-gagnon/mgl7460-tp2-linktograph-groovy")
        println(sha1filename)

        assertEquals("84d87d076dd2c25ddfed212a49c50c966a6e02d5", sha1filename)
    }

    /* todo : test groovy, faire test sur la methode de link */

    void testRemoveDuplicates() {
        /* Minimal test, no need to test collection implementation. */

        def lista = ["a", "a", "b"]
        def listb = ["a", "b", "a"]

        /* Comparison need to be done on sorted list, otherwise the list not considered the same. */
        /* first check = validate that list and listb are equal when sorted. */
        assert lista.sort().equals(listb.sort())

        /* Voir http://mrhaki.blogspot.com/2011/04/groovy-goodness-see-if-list-and-object.html */

        def listWithoutDuplicates = Link.removeDuplicates(lista)

        assert !listWithoutDuplicates.sort().equals(listb.sort())
        assert listWithoutDuplicates.size() == 2
        assert listWithoutDuplicates.count("a") == 1

    }

    void testReadFromUnexistingFile() {
        def list = Link.getLinksFromFile("_unexisting_file_path_")
        // Should not crash, just print a message and return a empty list.
        assert list.size() == 0
    }

    void testIsLinkValidURL() {
        assert Link.isLinkValidURL("http://https://github.com/eric-gagnon/mgl7460-tp2-linktograph-groovy")
        // todo_eg : add a filter for protocol, only http / https.
        // assert Link.isLinkValidURL("ftp://github.com/eric-gagnon/mgl7460-tp2-linktograph-groovy")
        assert !Link.isLinkValidURL("not_a_url")
    }
}