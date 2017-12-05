from urllib.parse import urlencode
import urllib.request
import json
import time
import hmac
import hashlib

class bittrex(object):
    
    def __init__(self):
        self.public = ['getmarkets', 'getcurrencies', 'getticker', 'getmarketsummaries', 'getmarketsummary', 'getorderbook', 'getmarkethistory']
        
    
    def query(self, method, values={}):
        if method in self.public:
            url = 'https://bittrex.com/api/v1.1/public/'
        elif method in self.market:
            url = 'https://bittrex.com/api/v1.1/market/'
        elif method in self.account: 
            url = 'https://bittrex.com/api/v1.1/account/'
        else:
            return 'Something went wrong, sorry.'
        
        url += method + '?' + urlencode(values)
        
        if method not in self.public:
            url += '&apikey=' + self.key
            url += '&nonce=' + str(int(time.time()))
            signature = hmac.new(self.secret, url, hashlib.sha512).hexdigest()
            headers = {'apisign': signature}
        else:
            headers = {}
        
        req = urllib.request.Request(url, headers=headers)
        response = json.loads(urllib.request.urlopen(req).read().decode('utf-8'))
        
        if response["result"]:
            return response["result"]
        else:
            return response["message"]
    
    
    def getmarkets(self):
        return self.query('getmarkets')
    
    def getcurrencies(self):
        return self.query('getcurrencies')
    
    def getticker(self, market):
        return self.query('getticker', {'market': market})
    
    def getmarketsummaries(self):
        return self.query('getmarketsummaries')
    
    def getmarketsummary(self, market):
        return self.query('getmarketsummary', {'market': market})
    
    def getorderbook(self, market, type, depth=20):
        return self.query('getorderbook', {'market': market, 'type': type, 'depth': depth})
    
    def getmarkethistory(self, market, count=20):
        return self.query('getmarkethistory', {'market': market, 'count': count})
