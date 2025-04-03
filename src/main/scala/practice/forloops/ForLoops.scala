package practice.forloops

object ForLoops {
    def main(args:Array[String]):Unit={
      for(i <- 0 to 3)
        println(i)

      //exclude end
      for(i <- 0 until 3)
        println(i)

      //exclude end
      for(i <- Range(0,10))
        println(i)
      //exclude end with step
      for(i <- Range(0,10,2))
        println(i)

      // multiple ranges

      for {
        i <- 0 to 3
        j <- 0 to 3
      } {
        println (s"$i, $j")
      }

      for{
        i <- 0 to 3
        j <- i to 3
      }{
        println(s"$i, $j")
      }
    }


}
