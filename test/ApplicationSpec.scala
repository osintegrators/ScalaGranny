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
    val result = controllers.Application.index()(FakeRequest())

    status(result) must equalTo(OK)
    contentType(result) must beSome("text/html")
  }

  "respond to the addresses Action" in new WithApplication {
    val result = controllers.Application.addresses()(FakeRequest())

    status(result) must equalTo(OK)
    contentType(result) must beSome("application/json")
  }
  
}