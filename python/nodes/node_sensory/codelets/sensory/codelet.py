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
#import pandas as pd
import numpy as np
import dct


class SensoryCodelet(dct.codelets.PythonCodelet):
    def __init__(self, name, root_codelet_dir):
        super().__init__(name, root_codelet_dir)
        self.data = np.load(os.getcwd()+ '/x_test.npy')  #pd.read_csv(os.getcwd()+ 'x_test.csv')
        self.index = 0


    '''def calculate_activation(self):
        print("new Activation")
        return 0.0
    '''

    def proc(self, activation):
        if self.data.size > 0: 
            entry = self.data[self.index]
            #entry = self.data.iloc[self.index]
            # self.data.drop(self.index, inplace=True)
            self.index += 1
            try:
                dct.set_memory_objects_by_name(self.root_codelet_dir, 'sensory-memory', 'I', entry.tolist(), 'outputs')
            except Exception as e:
                print(e)
        


if __name__ == '__main__':  
    codelet = SensoryCodelet(name='sensoryCodelet', root_codelet_dir=os.getcwd()+'/codelets/sensory')
    threading.Thread(target=codelet.run).start()

