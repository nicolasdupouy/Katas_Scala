import org.scalatest.{FeatureSpec, GivenWhenThen, Matchers}

class BowlingGameScenarioTest extends FeatureSpec with GivenWhenThen with Matchers {
  scenario("Bowling game") {
    Given("new game started")
    val game = new BowlingGame

    When("O pin is knocked in frame 1")
    game.roll(1)

    Then("Score is 0")
    game.score should be(0)
  }
}
