import org.scalatest.{FunSpec, Matchers}

class BowlingGameTest extends FunSpec with Matchers {
  val game = new BowlingGame

  describe("Bowling game") {
    it("should score 0 if all frame are failed") {
      for(i <- 1 to 10) {
        game.roll(0)
      }
      game.score() should be(0)
    }
  }
}
