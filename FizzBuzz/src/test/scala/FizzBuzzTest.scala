import org.scalatest.{FunSpec, Matchers}

class FizzBuzzTest extends FunSpec with Matchers {

  val fizzBuzz = new FizzBuzz

  describe("FizzBuzz tests") {
    it("should display 1 when parameter is 1") {
      fizzBuzz.display(1) should be ("1")
    }

    it("should display 2 when parameter is 2") {
      fizzBuzz.display(2) should be ("2")
    }

    it("should display Fizz when parameter is a multiple of 3") {
      fizzBuzz.display(3) should be ("Fizz")
      fizzBuzz.display(18) should be ("Fizz")
    }

    it("should display Buzz when parameter is a multiple of 5") {
      fizzBuzz.display(5) should be ("Buzz")
      fizzBuzz.display(10) should be ("Buzz")
    }

    it("should display FizzBuzz when parameter is a multiple of 3 and 5") {
      fizzBuzz.display(15) should be ("FizzBuzz")
      fizzBuzz.display(60) should be ("FizzBuzz")
    }
  }
}
