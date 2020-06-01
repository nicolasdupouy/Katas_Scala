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

    When("5 pins down in frame 2, roll 1")
    game.roll(5)
    When("the 5 others pins down in frame 2, roll 2 (spear roll not played yet)")
    game.roll(5)

    Then("score is 10")
    game.scorePatternMatching should be(12)
    game.scoreRecursive should be(12)

    When("7 pins down")
    game.roll(7)

    Then("score is 26 (12 + 7*2)")
    game.scorePatternMatching should be(26)
    game.scoreRecursive should be(26)
  }
}
