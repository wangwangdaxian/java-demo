package com.shizk.demo.java.tools.sketch;

import org.apache.datasketches.cpc.CpcSketch;
import org.apache.datasketches.cpc.CpcUnion;
import org.apache.datasketches.memory.Memory;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class CpcSketchDemo {
    public static void main(String[] args) throws Exception {
        final int lgK = 10;
        // this section generates two sketches with some overlap and serializes them into files
        {
            // 100000 distinct keys
            CpcSketch sketch1 = new CpcSketch(lgK);
            for (int key = 0; key < 100000; key++) sketch1.update(key);
            FileOutputStream out1 = new FileOutputStream("/tmp/sketch/CpcSketch1.bin");
            out1.write(sketch1.toByteArray());
            out1.close();

            // 100000 distinct keys
            CpcSketch sketch2 = new CpcSketch(lgK);
            for (int key = 50000; key < 150000; key++) sketch2.update(key);
            FileOutputStream out2 = new FileOutputStream("/tmp/sketch/CpcSketch2.bin");
            out2.write(sketch2.toByteArray());
            out2.close();
        }

        // this section deserializes the sketches, produces a union and prints the result
        {
            FileInputStream in1 = new FileInputStream("/tmp/sketch/CpcSketch1.bin");
            byte[] bytes1 = new byte[in1.available()];
            in1.read(bytes1);
            in1.close();
            CpcSketch sketch1 = CpcSketch.heapify(Memory.wrap(bytes1));

            FileInputStream in2 = new FileInputStream("/tmp/sketch/CpcSketch2.bin");
            byte[] bytes2 = new byte[in2.available()];
            in2.read(bytes2);
            in2.close();
            CpcSketch sketch2 = CpcSketch.heapify(Memory.wrap(bytes2));

            CpcUnion union = new CpcUnion(lgK);
            union.update(sketch1);
            union.update(sketch2);
            CpcSketch result = union.getResult();

            // debug summary of the union result sketch
            System.out.println(result.toString());

            System.out.println("Distinct count estimate: " + result.getEstimate());
            System.out.println("Distinct count lower bound 95% confidence: " + result.getLowerBound(2));
            System.out.println("Distinct count upper bound 95% confidence: " + result.getUpperBound(2));
        }
    }
}
