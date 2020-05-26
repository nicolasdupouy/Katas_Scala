import org.scalatest.{FunSpec, Matchers}

class FizzBuzzTest extends FunSpec with Matchers {

  val fizzBuzz = new FizzBuzz

  describe("FizzBuzz tests") {
    it("should display 1 when parameter is 1") {
      fizzBuzz.display(1) should be ("1")
    }
  }
}
