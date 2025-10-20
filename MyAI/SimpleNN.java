package MyAI;
// SimpleNN.java
import java.util.Random;
import java.util.Arrays;

public class SimpleNN {
    // 2-input, 2-hidden, 1-output neural net for XOR example
    private final int inputSize = 2;
    private final int hiddenSize = 2;
    private final int outputSize = 1;

    private double[][] W1; // hiddenSize x inputSize
    private double[] b1;   // hiddenSize
    private double[] W2;   // outputSize x hiddenSize (flattened)
    private double b2;     // output bias

    private final Random rnd = new Random(42);

    public SimpleNN() {
        W1 = new double[hiddenSize][inputSize];
        b1 = new double[hiddenSize];
        W2 = new double[hiddenSize];
        initParams();
    }

    private void initParams() {
        for (int i = 0; i < hiddenSize; i++) {
            for (int j = 0; j < inputSize; j++) W1[i][j] = rnd.nextGaussian() * 0.5;
            b1[i] = 0;
            W2[i] = rnd.nextGaussian() * 0.5;
        }
        b2 = 0;
    }

    private static double sigmoid(double x) {
        return 1.0 / (1.0 + Math.exp(-x));
    }

    private static double dsigmoid(double y) {
        return y * (1 - y); // assuming y = sigmoid(x)
    }

    // Forward pass: returns [hidden[], output]
    private double[] forward(double[] x, double[] hiddenOut) {
        for (int i = 0; i < hiddenSize; i++) {
            double sum = b1[i];
            for (int j = 0; j < inputSize; j++) sum += W1[i][j] * x[j];
            hiddenOut[i] = sigmoid(sum);
        }
        double outSum = b2;
        for (int i = 0; i < hiddenSize; i++) outSum += W2[i] * hiddenOut[i];
        double y = sigmoid(outSum);
        return new double[] {y};
    }

    // Train on XOR dataset
    public void train(int epochs, double lr) {
        double[][] X = { {0,0}, {0,1}, {1,0}, {1,1} };
        double[] Y = { 0, 1, 1, 0 };

        for (int e = 0; e < epochs; e++) {
            double loss = 0;
            for (int k = 0; k < X.length; k++) {
                double[] x = X[k];
                double target = Y[k];

                double[] h = new double[hiddenSize];
                double[] outArr = forward(x, h);
                double y = outArr[0];

                double err = target - y;
                loss += err * err;

                // backprop
                double dOut = err * dsigmoid(y); // dL/dy * dy/dnet_out

                // update W2 and b2
                for (int i = 0; i < hiddenSize; i++) {
                    double grad = dOut * h[i];
                    W2[i] += lr * grad;
                }
                b2 += lr * dOut;

                // hidden layer gradients
                double[] dHidden = new double[hiddenSize];
                for (int i = 0; i < hiddenSize; i++) {
                    dHidden[i] = dOut * W2[i] * dsigmoid(h[i]);
                }

                // update W1 and b1
                for (int i = 0; i < hiddenSize; i++) {
                    for (int j = 0; j < inputSize; j++) {
                        double grad = dHidden[i] * x[j];
                        W1[i][j] += lr * grad;
                    }
                    b1[i] += lr * dHidden[i];
                }
            }

            if (e % 1000 == 0) {
                System.out.printf("Epoch %d, MSE loss %.6f%n", e, loss / X.length);
            }
        }
    }

    public double predict(double[] x) {
        double[] h = new double[hiddenSize];
        double[] out = forward(x, h);
        return out[0];
    }

    // quick demo runner
    public static void demo() {
        SimpleNN nn = new SimpleNN();
        System.out.println("Training on XOR...");
        nn.train(10000, 0.1);

        double[][] X = { {0,0}, {0,1}, {1,0}, {1,1} };
        for (double[] x : X) {
            double p = nn.predict(x);
            System.out.printf("input=%s -> %.4f (rounded %d)%n", Arrays.toString(x), p, p > 0.5 ? 1 : 0);
        }
    }
}