/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.sysds.test.functions.aggregate;

import org.apache.sysds.parser.LanguageException;
import org.apache.sysds.test.AutomatedTestBase;
import org.apache.sysds.test.TestConfiguration;
import org.junit.Test;

/**
 * <p>
 * <b>Positive tests:</b>
 * </p>
 * <ul>
 * <li>general test</li>
 * </ul>
 * <p>
 * <b>Negative tests:</b>
 * </p>
 * <ul>
 * <li>scalar test</li>
 * </ul>
 * 
 * 
 */
public class SumTest extends AutomatedTestBase {

	private final static String TEST_DIR = "functions/aggregate/";
	private static final String TEST_CLASS_DIR = TEST_DIR + SumTest.class.getSimpleName() + "/";
	private final static String TEST_GENERAL = "General";
	private final static String TEST_SCALAR = "Scalar";

	@Override
	public void setUp() {
		// positive tests
		addTestConfiguration(TEST_GENERAL,
			new TestConfiguration(TEST_CLASS_DIR, "SumTest", new String[] {"vector_sum", "matrix_sum"}));

		// negative tests
		addTestConfiguration(TEST_SCALAR,
			new TestConfiguration(TEST_CLASS_DIR, "SumScalarTest", new String[] {"vector_sum", "matrix_sum"}));
	}

	@Test
	public void testGeneral() {
		int rows = 10;
		int cols = 10;

		TestConfiguration config = getTestConfiguration(TEST_GENERAL);
		config.addVariable("rows", rows);
		config.addVariable("cols", cols);

		loadTestConfiguration(config);

		createHelperMatrix();
		double[][] vector = getRandomMatrix(rows, 1, 0, 1, 1, -1);
		double vectorSum = 0;
		for(int i = 0; i < rows; i++) {
			vectorSum += vector[i][0];
		}
		writeInputMatrix("vector", vector);
		writeExpectedHelperMatrix("vector_sum", vectorSum);

		double[][] matrix = getRandomMatrix(rows, cols, -10, 10, 0.4, 7);
		double matrixSum = 0;
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				matrixSum += matrix[i][j];
			}
		}
		writeInputMatrix("matrix", matrix);
		writeExpectedHelperMatrix("matrix_sum", matrixSum);

		runTest();

		compareResults(5e-14);
	}

	@Test
	public void testScalar() {
		int scalar = 3;

		TestConfiguration config = getTestConfiguration(TEST_SCALAR);
		config.addVariable("scalar", scalar);

		createHelperMatrix();

		loadTestConfiguration(config);

		runTest(true, LanguageException.class);
	}
}
