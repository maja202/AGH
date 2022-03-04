# -*- coding: utf-8 -*-

from urllib import request

request.urlretrieve('https://raw.githubusercontent.com/ageron/handson-ml2/master/datasets/housing/housing.tgz', 'housing.tgz')

import tarfile
import os
import urllib
import gzip
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
from sklearn.model_selection import train_test_split
import pickle as pkl

with tarfile.open('housing.tgz') as handle:
    handle.extractall()
handle.close()

read = open('housing.csv')
write = gzip.open('housing.csv.gz', 'wt')
write.writelines(read)
read.close()
write.close()

df = pd.read_csv('housing.csv.gz')

df.head()

df.info()

print(df)

df.loc[:, "ocean_proximity"].value_counts()

df.loc[:, "ocean_proximity"].describe()

df.hist(bins=50, figsize=(20,15))

plt.savefig('obraz1.png')

f2 = df.plot(kind="scatter", x="longitude", y = "latitude", alpha = 0.1, figsize=(7,4)) 
fig2 = f2.get_figure()
fig2.savefig("obraz2.png")

f3 = df.plot(kind="scatter", x="longitude", y="latitude",
        alpha=0.4, figsize=(7,3), colorbar=True,
        s=df["population"]/100, label="population", 
        c="median_house_value", cmap=plt.get_cmap("jet"))
fig3 = f3.get_figure()
fig3.savefig("obraz3.png")

df.corr()["median_house_value"].sort_values(ascending=False).reset_index().rename(columns={"index":"atrybut", "median_house_value":"wspolczynnik_korelacji"}).to_csv('korelacja.csv', index = False)

sns.pairplot(df)

train_set, test_set = train_test_split(df, test_size=0.2, random_state=42)
len(train_set),len(test_set)

print(train_set)

print(test_set)

train_set.corr()["median_house_value"].sort_values(ascending=False)

test_set.corr()["median_house_value"].sort_values(ascending=False)

with open('train_set.pkl', 'wb') as handle : pkl.dump(train_set, handle, protocol=pkl.HIGHEST_PROTOCOL)

with open('test_set.pkl', 'wb') as handle : pkl.dump(test_set, handle, protocol=pkl.HIGHEST_PROTOCOL)