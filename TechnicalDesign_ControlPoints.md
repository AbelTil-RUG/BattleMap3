# Control Points

## Types

There are two types of control points.
- Control point can be (re)capped by all teams - example: Conquest
- Control point can only be capped by one team - example: Defend Control Point

### Recappable point
The point rests at a score of 0. In this case there is no active capturer.

**if currentScore == 0:**
| Case | Action |
|------|--------|
| 0 team                | do nothing
| 1 team, not owner     | team becomes active capturer 
| 1 team, owner         | do nothing 
| 2+ team               | do nothing 

**activeCapturer != null:**
| Case | Action |
|------|--------|
| 0 team                    | reduce slowly
| 1 team, not capturer      | reduce at speed
| 1 team, capturer          | increase at speed
| 2+ team, without capturer | reduce at speed 
| 2+ team, with capturer    | do nothing

if currentScore becomes 0 -> remove active capturer
if currentScore becomes maxScore -> set active capturer as owner






GetPlayersOnPointByTeam()