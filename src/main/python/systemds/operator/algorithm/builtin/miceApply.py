# -------------------------------------------------------------
#
# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
#   http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.
#
# -------------------------------------------------------------

# Autogenerated By   : src/main/python/generator/generator.py
# Autogenerated From : scripts/builtin/miceApply.dml

from typing import Dict, Iterable

from systemds.operator import OperationNode, Matrix, Frame, List, MultiReturn, Scalar
from systemds.utils.consts import VALID_INPUT_TYPES


def miceApply(X: Matrix,
              meta: Matrix,
              threshold: float,
              dM: Frame,
              betaList: List):
    """
     This Builtin function implements multiple imputation using Chained Equations (MICE)
    
     Assumption missing value are represented with empty string i.e ",," in CSV file  
     variables with suffix n are storing continuos/numeric data and variables with 
     suffix c are storing categorical data
    
    
    
    :param X: Data Matrix (Recoded Matrix for categorical features)
    :param mtea: A meta matrix with each rows storing values 1) mask of original matrix,
        2) information of columns with missing values on  original data 0 for no missing value in column and 1 otherwise
        3) dist values in each columns in original data 1 for continuous columns and colMax for categorical
    :param threshold: confidence value [0, 1] for robust imputation, values will only be imputed
        if the predicted value has probability greater than threshold,
        only applicable for categorical data
    :param dM: meta frame from OHE on original data
    :param betaList: List of machine learning models trained for each column imputation
    :param verbose: Boolean value.
    :return: imputed dataset
    """

    params_dict = {'X': X, 'meta': meta, 'threshold': threshold, 'dM': dM, 'betaList': betaList}
    return Matrix(X.sds_context,
        'miceApply',
        named_input_nodes=params_dict)
