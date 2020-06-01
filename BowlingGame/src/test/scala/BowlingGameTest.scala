import org.scalatest.{FunSpec, Matchers}

class BowlingGameTest extends FunSpec with Matchers {
  val game = new BowlingGame

  describe("Bowling game") {
    it("should score 0 if all frames are failed") {
      for(i <- 1 to 20) {
        game.roll(0)
      }

      game.scorePatternMatching should be(0)
      game.scoreRecursive should be(0)
    }
  }
}
