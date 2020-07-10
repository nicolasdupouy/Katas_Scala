import scala.annotation.tailrec

class BowlingGame {
  private var frames = List[Frame]()

  def roll(pins: Int): Unit = {
    if (frames.nonEmpty && frames.last.isLastFrame) {
      val l = frames.last.asInstanceOf[TenthFrame]
      if (!l.roll2Done) {
        l.roll2Done = true
        l.addRoll(pins)
      }
      else l.roll3 = pins
    }
    else if (
      frames.nonEmpty
        && frames.last.isNotFinished)
      frames.last.addRoll(pins)
    else if (frames.size == 9)
      frames = frames :+ new TenthFrame(pins)
    else
      frames = frames :+ Frame(pins)
  }

  def scorePatternMatching(): Int = {
    def pm(bowlingFrames: List[Frame]): Int = bowlingFrames match {
      case Nil => 0
      case h :: Nil if h.isLastFrame && (h.isStrike || h.isSpear) => h.sumRolls + h.asInstanceOf[TenthFrame].roll3
      case h :: Nil if h.isLastFrame => h.sumRolls
      case h :: Nil => h.getRoll1 + h.getRoll2
      case h :: t if h.isStrike && t.head.isStrike && t.head.isLastFrame => h.sumRolls + t.head.sumRolls + pm(t)
      case h :: t if h.isStrike && t.head.isStrike => h.sumRolls + t.head.getRoll1 + t.tail.head.getRoll1 + pm(t)
      case h :: t if h.isStrike => scoreWhenStrike(h, t.head) + pm(t)
      case h :: t if h.isSpear => scoreWhenSpear(h, t.head) + pm(t)
      case h :: t => h.sumRolls + pm(t)
    }

    pm(this.frames)
  }

  def scoreRecursive(): Int = {
    @tailrec
    def loop(bowlingFrames: List[Frame], computedScore: Int): Int = {
      if (bowlingFrames.isEmpty)
        computedScore
      else if (bowlingFrames.head.isLastFrame && (bowlingFrames.head.isStrike || bowlingFrames.head.isSpear))
        loop(bowlingFrames.tail, computedScore + bowlingFrames.head.sumRolls + bowlingFrames.head.asInstanceOf[TenthFrame].roll3)
      else if (bowlingFrames.head.isStrike && bowlingFrames.tail.nonEmpty && bowlingFrames.tail.head.isLastFrame)
        loop(bowlingFrames.tail, computedScore + bowlingFrames.head.sumRolls + bowlingFrames.tail.head.sumRolls)
      else if (bowlingFrames.head.isStrike && bowlingFrames.tail.nonEmpty && bowlingFrames.tail.head.isStrike)
        loop(bowlingFrames.tail, computedScore + bowlingFrames.head.sumRolls + bowlingFrames.tail.head.getRoll1 + bowlingFrames.tail.tail.head.getRoll1)
      else if (bowlingFrames.head.isStrike && bowlingFrames.tail.nonEmpty)
        loop(bowlingFrames.tail, computedScore + scoreWhenStrike(bowlingFrames.head, bowlingFrames.tail.head))
      else if (bowlingFrames.head.isSpear && bowlingFrames.tail.nonEmpty)
        loop(bowlingFrames.tail, computedScore + scoreWhenSpear(bowlingFrames.head, bowlingFrames.tail.head))
      else
        loop(bowlingFrames.tail, computedScore + bowlingFrames.head.sumRolls)
    }

    loop(this.frames, 0)
  }

  private def scoreWhenSpear(currentFrame: Frame, nextFrame: Frame): Int = currentFrame.sumRolls + nextFrame.getRoll1
  private def scoreWhenStrike(currentFrame: Frame, nextFrame: Frame): Int = currentFrame.sumRolls + nextFrame.sumRolls
}
