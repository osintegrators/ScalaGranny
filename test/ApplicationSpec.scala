package test

import org.specs2.mutable._

import play.api.test._
import play.api.test.Helpers._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
class ApplicationSpec extends Specification {

    "respond to the index Action" in {
        running(FakeApplication()) {
	        val result = controllers.Application.address()(FakeRequest())
	        
	        status(result) must equalTo(OK)
	        contentType(result) must beSome("text/html")
        }
    }
}