import org.scalatest.{FunSpec, Matchers}

class BowlingGameTest extends FunSpec with Matchers {
  describe("Bowling game") {
    it("should score 0 if all frames are failed") {
      val game = new BowlingGame
      for(_ <- 1 to 20) {
        game.roll(0)
      }

      game.scorePatternMatching should be(0)
      game.scoreRecursive should be(0)
    }

    it("should score 300 for a perfect game") {
      val game = new BowlingGame
      for(_ <- 1 to 12) {
        game.roll(10)
      }

      game.scorePatternMatching should be(300)
      game.scoreRecursive should be(300)
    }
  }
}
