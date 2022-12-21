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

ndfQ = ndf[ndf['quarter'].str.contains('Q')]
ndfE = ndf[ndf['quarter'].str.contains('E')]

plt.subplots(constrained_layout=True)

plt.subplot(221)
plt.title('Quarter revenue')
plt.plot(ndfQ['quarter'],ndfQ['revenue'],'g')
plt.xticks(rotation=90,fontsize=6.5)

plt.subplot(222)
plt.title('Yearly revenue')
plt.plot(ndfE['quarter'],ndfE['revenue'],'g')
plt.xticks(fontsize=6.5)

plt.subplot(223)
plt.title('Quarter income, profit')
plt.plot(ndfQ['quarter'],ndfQ['income'])
plt.plot(ndfQ['quarter'],ndfQ['profit'])
plt.xticks(rotation=90,fontsize=6.5)

plt.subplot(224)
plt.title('Yearly income, profit')
plt.plot(ndfE['quarter'],ndfE['income'])
plt.plot(ndfE['quarter'],ndfE['profit'])
plt.xticks(fontsize=6.5)

plt.savefig(gcwd+'/coname_graph/graphs/'+user+'.png')