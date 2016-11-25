

/**
 * @author Stephen A. Friedman
 * 2014 Teams's Runs per game average  vs 2015 Run Line
 */
import scala.io.Source
import java.util.Scanner
import java.util.ArrayList
object Run {
  def main(args: Array[String]) {
    var teamAndRPG: scala.collection.mutable.Map[String, Double] = scala.collection.mutable.Map()
    var teamAndMoneyTotal: scala.collection.mutable.Map[String, Integer] = scala.collection.mutable.Map()
    /* FROM LAHMAN.TEAMS
     * 
     * select teamID,name, R/G as RPG from lahman.teams
       where yearID = 2014 
       order by teamID asc
     */
    teamAndRPG += ("Arizona" -> 3.7963, "Atlanta" -> 3.537, "Baltimore" -> 4.3519, "Boston" -> 3.9136, "ChiCubs" -> 4.0741, "ChiSox" -> 3.7901, "Cincinnati" -> 3.6728, "Cleveland" -> 4.1296, "Colorado" -> 4.6605, "Detroit" -> 4.6728, "Houston" -> 3.8827, "KansasCity" -> 4.0185, "LAAngels" -> 4.7716, "LADodgers" -> 4.4321, "Miami" -> 3.9815, "Milwaukee" -> 4.0123, "Minnesota" -> 4.4136, "NYMets" -> 3.8827, "NYYankees" -> 3.9074, "Oakland" -> 4.5, "Philadelphia" -> 3.821, "Pittsburgh" -> 4.2099, "SanDiego" -> 3.3025, "Seattle" -> 3.9136, "SFGiants" -> 4.1049, "St.Louis" -> 3.821, "TampaBay" -> 3.7778, "Texas" -> 3.9321, "Toronto" -> 4.4630, "Washington" -> 4.2346)
    teamAndMoneyTotal += ("Arizona" -> 0, "Atlanta" -> 0, "Baltimore" -> 0, "Boston" -> 0, "ChiCubs" -> 0, "ChiSox" -> 0, "Cincinnati" -> 0, "Cleveland" -> 0, "Colorado" -> 0, "Detroit" -> 0, "Houston" -> 0, "KansasCity" -> 0, "LAAngels" -> 0, "LADodgers" -> 0, "Miami" -> 0, "Milwaukee" -> 0, "Minnesota" -> 0, "NYMets" -> 0, "NYYankees" -> 0, "Oakland" -> 0, "Philadelphia" -> 0, "Pittsburgh" -> 0, "SanDiego" -> 0, "Seattle" -> 0, "SFGiants" -> 0, "St.Louis" -> 0, "TampaBay" -> 0, "Texas" -> 0, "Toronto" -> 0, "Washington" -> 0)
    var totalAmountBet = 0;
    var team1 = true
    var team2 = false
    var dLine1: Array[String] = null
    var dLine2: Array[String] = null
    val myFile = "BettingOdds.txt"
    for (line <- Source.fromFile(myFile).getLines()) {
      println("Team1: " + team1  + "     Team2: "+team2)
      if (team1 == true) {
        println("Line length: "+line.length())
        if (line.length() > 10) {
          dLine1 = line.split("""\s+""")
          println("here")
          team1=false
        }
        //team1 = false
      }
      if (team2 == true) {
        if (line.length() > 10) {
          dLine2 = line.split("""\s+""")
        }
        team2 = false
        team1 = true

        var AwayTeam = dLine1(0)
        println("AwayTeam: "+AwayTeam)
        var HomeTeam = dLine2(0)
        println("HomeTeam: "+HomeTeam)
        var AwayScore = dLine1(1).toInt
        println("AwayScore: "+AwayScore)
        var HomeScore = dLine2(1).toInt
        println("HomeScore: "+HomeScore)
        var AwayTeamRL = dLine1(4).trim().substring(0).toDouble
        println("AwayTeamRL: "+AwayTeamRL)
        var HomeTeamRL = dLine2(4).trim().substring(0).toDouble
        println("HomeTeamRL: "+HomeTeamRL)
        var AwayTeamLine = dLine1(5).trim().substring(0).toInt //* dLine1(5).trim().substring(0).toInt / dLine1(5).trim().substring(1).toInt
        println("AwayTeamLine: "+AwayTeamLine)
        var HomeTeamLine = dLine2(5).trim().substring(0).toInt //* dLine2(5).trim().substring(0).toInt / dLine2(5).trim().substring(1).toInt
        println("HomeTeamLine: "+HomeTeamLine)
        var AwayTeamPM = AwayScore - HomeScore
        println("AwayTeamPM: "+AwayTeamPM)
        var HomeTeamPM = HomeScore - AwayScore
        println("HomeTeamPM: "+HomeTeamPM)
        var AwinBet = (AwayTeamPM + AwayTeamRL) > 0
        println("AwinBet: "+AwinBet)
        var HwinBet = (HomeTeamPM + HomeTeamRL) > 0
        println("HwinBet: "+HwinBet)
        var AwayRunsPerGame = teamAndRPG(AwayTeam)
        println("AwayRunsPerGame: "+AwayRunsPerGame)
        var HomeRunsPerGame = teamAndRPG(HomeTeam)
        println("HomeRunsPerGame: "+HomeRunsPerGame)
        var AwayRunsPerGamePM = AwayRunsPerGame - HomeRunsPerGame
        println("AwayRunsPerGamePM: "+AwayRunsPerGamePM)
        var HomeRunsPerGamePM = HomeRunsPerGame - AwayRunsPerGame
        println("HomeRunsPerGamePM: "+HomeRunsPerGamePM)
        var AwayToBet = (AwayRunsPerGamePM + AwayTeamRL) > 0
        println("AwayToBet: "+AwayToBet)
        var HomeToBet = (HomeRunsPerGamePM + HomeTeamRL) > 0
        println("HomeToBet: "+HomeToBet)
        var AwayBet = 0
        //println("AwayBet: "+AwayBet)
        var HomeBet = 0
        //println("HomeBet: "+HomeBet)
        var AwayWinTotal = 0
        //println("AwayWinTotal: "+AwayWinTotal)
        var HomeWinTotal = 0
        //println("HomeWinTotal: "+HomeWi nTotal)
        
        if (AwayToBet == true) {
          var AwayFave = AwayTeamLine < 0
          if (AwayFave == true) {
            AwayBet = -AwayTeamLine
            println("AwayBet: "+AwayBet)
          } else {
            AwayBet = 100
            println("AwayBet: "+AwayBet)
          }

          if (AwinBet && AwayFave) {
            AwayWinTotal = 100
            println("AwayWinTotal: "+AwayWinTotal)
          }

          if (AwinBet && !AwayFave) {
            AwayWinTotal = AwayTeamLine
            println("AwayWinTotal: "+AwayWinTotal)
          }

          if (!AwinBet) {
            //AwayWinTotal = -1 * AwayBet
            AwayWinTotal -= AwayBet
            println("Away team lost bet: $"+AwayWinTotal)
          }
          
          totalAmountBet += AwayBet
        }

        if (HomeToBet == true) {
          var HomeFave = HomeTeamLine < 0
          if (HomeFave == true) {
             println("HomeBet: "+HomeBet)
            HomeBet = -HomeTeamLine
          } else {
            HomeBet = 100
             println("HomeBet: "+HomeBet)
          }

          if (HwinBet && HomeFave) {
            HomeWinTotal = 100
             println("HomeWinTotal: "+HomeWinTotal)
          }

          if (HwinBet && !HomeFave) {
            HomeWinTotal = HomeTeamLine
            println("HomeWinTotal: "+HomeWinTotal)
          }

          if (!HwinBet) {
            HomeWinTotal -= HomeBet
            println("Home team lost bet: $"+HomeWinTotal)
          }
            totalAmountBet += HomeBet
        }
          
        //update the teamAndMoneyTotal
        teamAndMoneyTotal(AwayTeam) = teamAndMoneyTotal(AwayTeam) + AwayWinTotal
        println(AwayTeam+ " TOTAL $"+teamAndMoneyTotal(AwayTeam))
        teamAndMoneyTotal(HomeTeam) = teamAndMoneyTotal(HomeTeam) + HomeWinTotal
        println(HomeTeam+ " TOTAL $"+teamAndMoneyTotal(HomeTeam))
        
      }
      if (team1 == false) {
        team2 = true
      }
      if (team2 == false) {
        team1 = true
      }
    }
    println("--------------------------------------------------------------------")
    teamAndMoneyTotal.foreach(x => println (x._1 +"   $" + x._2))
    var totalWinnings = 0
    teamAndMoneyTotal.foreach(x =>(totalWinnings+=x._2) )
    println("")
    println("NET GAIN/LOSS: $"+totalWinnings)
    println("TOTAL AMOUNT GAMBLED: $"+totalAmountBet)
    println("RATE OF RETURN: "+totalWinnings.toDouble/totalAmountBet.toDouble*100+"%")
    
  }
}