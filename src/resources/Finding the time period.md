# Task

A museum recorded the entering and leaving time for each visitor during the day with one minute precision. This resulted in N pairs, where the first value indicates the start and the second the end time of the visit. Find the time period when there were the most number of visitors in the museum and how many were there. The start and end of the visit time are both inclusive. For example, if one visitor arrived at 11:10 and the other left at 11:10, it is considered that at 11:10 there were 2 visitors in the museum.

# Solution

Main class to solve museum problem. Basic idea to solve this challenge is to fold intervals into list if entries (how many people entered or left museum ant given time). Then we sort list of entries and calculate elementary intervals to keep number of visitors in given interval. This is not very optimal, greedy O(n) (not counting initial sort) solution but it's quite simple. Probably one can come up with more sophisticated solution using augumented Interval Trees and reduce this time to O(log n). 

Basic idea behind with this set of intervals as an example:
 
  7 8  9   10 11 12     13     14  15
  [--------------------------------]
    [------] [---]       [-----]
        [-------------------]
        9:30                13:30
#v112222333223333222222223332221111 (number on visitors in museum within the time)

we end up with
[7:00, 8:00] with one visitor
[9:00, 9:00] with two visitors
[9:30, 10:00] with three visitors
[10:00, 11:00] with two  visitors
 etc.

Then we basically find interval with most visitors in it. If we have multiple periods with the same number of (maximum) concurrent visitors. This implementation will find only one (earliest) interval, according to the problem statement, but it's trivial to find all intervals.
 