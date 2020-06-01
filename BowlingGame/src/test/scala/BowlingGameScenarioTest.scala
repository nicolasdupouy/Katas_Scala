import org.scalatest.{FeatureSpec, GivenWhenThen, Matchers}

class BowlingGameScenarioTest extends FeatureSpec with GivenWhenThen with Matchers {
  scenario("Bowling game") {
    Given("new game started")
    val game = new BowlingGame

    When("O pin down in frame 1, roll 1")
    game.roll(0)

    Then("score is 0")
    game.scorePatternMatching should be(0)
    game.scoreRecursive should be(0)

    When("2 pins down in frame 1, roll 2")
    game.roll(2)

    Then("score is 2")
    game.scorePatternMatching should be(2)
    game.scoreRecursive should be(2)
  }
}
