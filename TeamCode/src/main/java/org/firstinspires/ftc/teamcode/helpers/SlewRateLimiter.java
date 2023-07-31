package org.firstinspires.ftc.teamcode.helpers;

import com.qualcomm.robotcore.util.ElapsedTime;

public class SlewRateLimiter {
    private final double m_positiveRateLimit;
    private final double m_negativeRateLimit;
    private double m_prevVal;
    private double m_prevTime;
    private final ElapsedTime timer = new ElapsedTime(ElapsedTime.Resolution.SECONDS);

    public SlewRateLimiter(double positiveRateLimit, double negativeRateLimit, double initialValue) {
        this.m_positiveRateLimit = positiveRateLimit;
        this.m_negativeRateLimit = negativeRateLimit;
        this.m_prevVal = initialValue;
        this.m_prevTime = timer.time();
    }

    public SlewRateLimiter(double rateLimit, double initalValue) {
        this(rateLimit, -rateLimit, initalValue);
    }

    public SlewRateLimiter(double rateLimit) {
        this(rateLimit, -rateLimit, 0.0);
    }

    public double calculate(double input) {
        double currentTime = timer.time();
        double elapsedTime = currentTime - this.m_prevTime;

        this.m_prevVal +=  Math.max(input - this.m_prevVal, Math.min(this.m_negativeRateLimit * elapsedTime, this.m_positiveRateLimit * elapsedTime));
        this.m_prevTime = currentTime;
        return this.m_prevVal;
    }

    public void reset(double value) {
        this.m_prevVal = value;
        this.m_prevTime = timer.time();
    }
}
