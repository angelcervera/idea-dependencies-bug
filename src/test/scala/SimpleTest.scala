import org.scalatest.{BeforeAndAfter, FunSuite, GivenWhenThen}

class SimpleTest extends FunSuite with GivenWhenThen with BeforeAndAfter {
  test("Simple Test") {
    Given("Why this error?")
    assert("ok" === "ok")
  }
}
