package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import kotlin.math.UMathKt;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
<<<<<<< HEAD
                        drive.trajectorySequenceBuilder(new Pose2d(0, 0, 0))
                                .back(40)
                                .strafeLeft(10)
                                .waitSeconds(2)
                                .strafeRight(10)
                                .forward(40)
                                .waitSeconds(1)
                                .build()
                );
=======
                        drive.trajectorySequenceBuilder(new Pose2d(0, 0, Math.toRadians(0)))
                                .waitSeconds(2)
                                .back(30)
                                .waitSeconds(2)
                                .forward(30)
                                .waitSeconds(2)





                        .build());
>>>>>>> a585cb708fa245033eace4f6efd9364344af633a

        meepMeep.setBackground(MeepMeep.Background.GRID_GRAY)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}