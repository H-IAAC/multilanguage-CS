# ****************************************************************************#
# Copyright (c) 2022  Wandemberg Gibaut                                       #
# All rights reserved. This program and the accompanying materials            #
# are made available under the terms of the MIT License                       #
# which accompanies this distribution, and is available at                    #
# https://opensource.org/licenses/MIT                                         #
#                                                                             #
# Contributors:                                                               #
#      W. Gibaut                                                              #
#                                                                             #
# ****************************************************************************#
import os
import threading
import pickle
import dct
from sklearn.tree import DecisionTreeClassifier
import numpy as np


class PerceptualCodelet(dct.codelets.PythonCodelet):
    def __init__(self, name, root_codelet_dir, model_option='decision_tree'):
        super().__init__(name, root_codelet_dir)
        self.model = pickle.load(open(os.getcwd() + f'/models/{model_option}.pk', 'rb'))


    '''def calculate_activation(self):
        print("new Activation")
        return 0.0
    '''

    def proc(self, activation):
        
        try:
            entry = np.array(dct.get_memory_objects_by_name(self.root_codelet_dir, 'sensory-memory', 'inputs')[0][0]['I']).reshape(1, -1)
            prediction = self.model.predict(entry)
            dct.set_memory_objects_by_name(self.root_codelet_dir, 'perceptual-memory', 'I', prediction.tolist(), 'outputs')
        except Exception as e:
            print(e)

        


if __name__ == '__main__':
    model_option = 'decision_tree'
    
    codelet = PerceptualCodelet(name='perceptualCodelet', root_codelet_dir=os.getcwd()+'/codelets/perceptual', model_option=model_option)
    threading.Thread(target=codelet.run).start()

