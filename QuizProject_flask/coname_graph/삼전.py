import pandas as pd
import time
import matplotlib.pyplot as plt
from matplotlib.figure import Figure
import glob
import os

gcwd = os.getcwd()
print(gcwd)
user = '삼성전자'
filepath = glob.glob(gcwd+'/coname_graph/*'+user+'.csv')

cofile = pd.read_csv(filepath[0])
newcolumns = ['quarter','revenue','income','profit']
cofile = cofile.values.tolist()
ndf = pd.DataFrame(cofile, columns=newcolumns)

plt.plot(ndf['quarter'],ndf['revenue'])
plt.savefig(gcwd+'/coname_graph/graphs/'+user+'.png')