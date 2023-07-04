import time
import subprocess
from dct import utils


def build_up_sensory_node(sensory_port, base_ip='localhost'): #5000    
    name = 'sensor_0'
    port = sensory_port
    istring = 'none sensory-memory@redis@' + base_ip + ':' + str(port+1)


    # up the node/codelet
    # TODO: be aware of the group part
    input_string = 'sensory ' + istring
    
    utils.add_node_to_system('nodes/node_sensory', base_ip + ':' + str(port), name, input_string)

    time.sleep(1)


def build_up_perceptual_node(sensory_port, perceptual_port, base_ip='127.0.0.1'): #5000
    name_base = 'perceptual'
    
    name = 'perceptual_0'
    port = perceptual_port

    # up the node/codelet
    # TODO: be aware of the group part
    input_string = 'sensory-memory@tcp@' + base_ip + ':' + str(sensory_port)
    all_string = 'perceptual ' + input_string + ' ' + name_base + '-memory@redis@' + base_ip + ':' + str(port+1)
    
    utils.add_node_to_system('nodes/node_percept', base_ip + ':' + str(port), name, all_string)
        

    time.sleep(1)


def build_up_all_nodes(sensory_port, perceptual_port, base_ip='127.0.0.1'):
    build_up_sensory_node(sensory_port, base_ip)
    build_up_perceptual_node(sensory_port, perceptual_port, base_ip)



def kill_all_codelets():
    subprocess.check_call('docker stop $(docker ps -a -q) && docker rm $(docker ps -a -q)', shell=True)


if __name__ == '__main__':
    build_up_all_nodes(5000, 5002)
    time.sleep(5*60)
    kill_all_codelets()
