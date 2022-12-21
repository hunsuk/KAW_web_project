from flask import Flask, render_template, request, send_file, abort
from user_info import MyEmpDao
import pandas as pd
import time
from flask_cors import CORS
import matplotlib.pyplot as plt
from matplotlib.figure import Figure
import glob
import os
from io import BytesIO, StringIO
import base64

app = Flask(__name__)
app.config['JSON_AS_ASCII'] = False
CORS(app, resources={r'*': {'origins': 'https://127.0.0.1:8080'}})


@app.route('/user',methods=['GET', 'POST'])
def user():
    # empList = MyEmpDao().getEmps(1)
    # print(empList[0]['email'])
    if request.method == 'GET':
        return render_template('user_input.html')
    else:
        totime = time.strftime('%Y-%m-%d', time.localtime(time.time()))
        print(request.files['file'])
        f = request.files['file']
        name = request.form
        coname = ""
        for key, value in name.items():
            coname = value

        data_csv = pd.read_csv(f)
        data_csv.to_csv('coname_input_data/' + totime + '-' +coname + ".csv",index=False)
        
        return data_csv.to_html()


@app.route('/predict')
def predict():
    user = '기아'
    if request.args.get('user') != None and request.args.get('user') != '':
        user = request.args.get('user')
        gcwd = os.getcwd()
        filepath = glob.glob(gcwd+'/coname_graph/*'+user+'.csv')
        cofile = pd.read_csv(filepath[0])
        newcolumns = ['quarter','revenue','income','profit']
        cofile = cofile.values.tolist()
        ndf = pd.DataFrame(cofile, columns=newcolumns)

        ndfQ = ndf[ndf['quarter'].str.contains('Q')]
        ndfE = ndf[ndf['quarter'].str.contains('E')]
        
        if os.path.isdir(gcwd+'/static/graphs/'+user)==False:
            os.mkdir(gcwd+'/static/graphs/'+user)
        
            plt.title('Quarter revenue')
            plt.plot(ndfQ['quarter'],ndfQ['revenue'],'g')
            plt.xticks(rotation=90,fontsize=6.5)

            plt.savefig(gcwd+'/static/graphs/'+user+'/'+user+'_Quarter'+'.png')
            plt.clf()

            plt.title('Yearly revenue')
            plt.plot(ndfE['quarter'],ndfE['revenue'],'g')
            plt.xticks(fontsize=6.5)

            plt.savefig(gcwd+'/static/graphs/'+user+'/'+user+'_Yearly'+'.png')
            plt.clf()



            plt.title('Quarter income, profit')
            plt.plot(ndfQ['quarter'],ndfQ['income'])
            plt.plot(ndfQ['quarter'],ndfQ['profit'])
            plt.xticks(rotation=90,fontsize=6.5)

            plt.savefig(gcwd+'/static/graphs/'+user+'/'+user+'_income_Quarter'+'.png')
            plt.clf()


            plt.title('Yearly income, profit')
            plt.plot(ndfE['quarter'],ndfE['income'])
            plt.plot(ndfE['quarter'],ndfE['profit'])
            plt.xticks(fontsize=6.5)


            plt.savefig(gcwd+'/static/graphs/'+user+'/'+user+'_income_Yea'+'.png')
            plt.clf()
        return render_template('admin_input.html', name = user, image_file_Quarter = 'graphs/'+user+'/'+user+'_Quarter'+'.png', image_file_Yearly= 'graphs/'+user+'/'+user+'_Yearly'+'.png', 
        image_file_Quarter_income= 'graphs/'+user+'/'+user+'_income_Quarter'+'.png',image_file_Yearly_income = 'graphs/'+user+'/'+user+'_income_Yea'+'.png')
    else:
        return render_template('page_not_found.html'), 404


@app.route('/admin')
def admin():
    # empList = MyEmpDao().getEmps(1)
    # print(empList[0]['email'])
    return render_template('admin_input.html')

if __name__ == '__main__':
    app.run()