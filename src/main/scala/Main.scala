import scala.annotation.tailrec
import scala.collection.mutable.ArrayBuffer

object Main extends App {
  var inputs = ArrayBuffer[String]()
  for (ln <- io.Source.stdin.getLines()) {
    inputs.append(ln)
  }
  val numVals = /*3*/inputs(0).toInt
  val numSeq = /*List("234325", "999", "81008", "808", "2133")*/ inputs.tail.toList

  numSeq.foreach(sym => println(nextSym(sym)))

  def nextSym(num: String) : String = {
    val (l, r) = lr(num)
    val odd = num.length % 2 == 1
    // symmetrical pairs need to be bumped up to next value
    val oneSide = if (l == r.reverse) (BigInt(l) + 1).toString else compareLR(l, r.reverse, l.length - 1)
    val expanded = oneSide.length > l.length

    // logic of combining the returned half:
    // if (even and not expanded):  oneSide + oneSide.reverse
    // if (even and expanded):      oneside + oneSide.reverse.drop(1)
    // if (odd and not expanded):   oneside + oneSide.reverse.drop(1)
    // if (odd and expanded):       oneside.take(oneSide.length - 1) + oneSide.reverse.drop(1)

    if (!odd && !expanded) oneSide + oneSide.reverse
    else if(odd && expanded) oneSide.take(oneSide.length - 1) + oneSide.reverse.drop(1)
    else oneSide + oneSide.reverse.drop(1)
  }

  def lr(inp: String): (String, String) = {
    val len = inp.length
    val mid = (len + 1) / 2
    (inp.take(mid), inp.drop(len - mid))
  }

  @tailrec
  def compareLR(left: String, right: String, i: Int): String = {
    if (i < 0) left
    else {
      if (left(i) > right(i)) left
      else if (left(i) < right(i)) (BigInt.apply(left) + 1).toString
      else compareLR(left, right, i - 1)
    }
  }
}