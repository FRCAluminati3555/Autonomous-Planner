# Autonomous-Planner
### The Goal ### 
The aim of this project is to provide a way for teams to coordinate with each others autonomous paths. This is because there are so many configurations that people can start to get everything confused. This provides a visual aid to better communicate what will be done in all of the cases. 

### What is Lot? Why Is There Extra Code Around That Never Gets Used? ###
The underlying skeleton of this project is from a game that I started programming this summer: https://github.com/Same4254/2.5D-Life. This project runs on a stripped down version of the game. The engine itself that this runs on was not programmed by me: https://github.com/QuantumCoding/Engine. What I use this engine for is mainly rendering, shading, and a tad bit of physics. Why wouldn't I use a game engine like Unity or Unreal? Well, this engine is more bare-bones, meaning that a lot of the things that traditional engines have, I would have to create on my own. Meaning that I learn substantially more in the process. One great example of this is that in unity a pathfinding algorithm is only a few method calls and the object moves to that position. However, when I did it myself, I learned all about astar pathfinding in order to find the quickest path.

However, since this is based on a MUCH larger project, it is likely that some odd wording will be in some of the code. This is because this is from another project, but used for the purposes of planning the autonomous before a match. 

## NOTE ##
This is not a simulation program. While it does have the features of relatively to scale models and velocities, this is for planning autonomous, providing insight to roughly how it will go rather than a full simulation program with physics.

## What do I have planned for this project to be? ##
In the end, I want this project to have the capabilities of taking in models, and scaling them to the world and placing them as if in autonomous. Thereafter, an alliance can plan out paths for autonomous for each case, ESPECIALLY for 2018 where there are several starting cases that can be mixed up when talking in the pits. Eventually, I want to be able to export these paths into a file that can be given to the other teams in the alliance. With this, another team could write an interpeter to read the path data and generate the autonomous dynamically. Additionally, I would like to add a point counter and timer to show the score and the total time it takes for auto to complete. This allows for the alliance to visually see how much they will score and how long it will take for them to complete auto. 
