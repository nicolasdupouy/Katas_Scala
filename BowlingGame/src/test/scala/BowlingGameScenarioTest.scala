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

    When("7 pins down in frame 3, roll 1")
    game.roll(7)

    Then("score is 26 (12 + 7*2)")
    game.scorePatternMatching should be(26)
    game.scoreRecursive should be(26)

    When("3 pins down in frame 3, roll 2 (second spear)")
    game.roll(3)

    Then("score is 29")
    game.scorePatternMatching should be(29)
    game.scoreRecursive should be(29)

    When("2 pins down in frame 4, roll 1")
    game.roll(2)

    Then("score is 33 (29 + 2*2)")
    game.scorePatternMatching should be(33)
    game.scoreRecursive should be(33)

    When("7 pins down in frame 4, roll 2")
    game.roll(7)

    Then("score is 40")
    game.scorePatternMatching should be(40)
    game.scoreRecursive should be(40)

    When("strike in frame 5, roll 1")
    game.roll(10)

    Then("score is 50")
    game.scorePatternMatching should be(50)
    game.scoreRecursive should be(50)

    When("8 pins down in frame 6, roll 1")
    game.roll(8)

    When("1 pin down in frame 6, roll 2")
    game.roll(1)

    Then("score is 68 (50 + (8 + 1)*2)")
    game.scorePatternMatching should be(68)
    game.scoreRecursive should be(68)

    When("strike in frame 7, roll 1")
    game.roll(10)

    When("strike in frame 8, roll 1")
    game.roll(10)

    When("1 pin down  in frame 9, roll 1")
    game.roll(1)
    When("2 pins down  in frame 9, roll 2")
    game.roll(2)

    Then("score is 105 (68 + frame 7:[10+10+1] + frame 8:[10+1+2] + frame 9:[1+2])")
    game.scorePatternMatching should be(105)
    game.scoreRecursive should be(105)

    When("7 pins down in frame 10, roll 1")
    game.roll(7)
    When("3 pins down in frame 10, roll 2")
    game.roll(3)
    When("7 pins down in frame 10, roll 3")
    game.roll(7)

    Then("final score is (105 + 7 + 3 + 7(spear)")
    game.scorePatternMatching should be(122)
    game.scoreRecursive should be(122)
  }
}
