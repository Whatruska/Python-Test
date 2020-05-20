import json
class Person:
    __props = dict()

    def __init__(self, fileName):
        self.read_from_file(fileName)

    def read_from_file(self, fileName):
        self.__props = json.load(open(fileName, 'r'), encoding='utf-8')

    def __str__(self):
        str = ""
        for elem in self.__props:
            str += "{0} : {1}\n".format(elem, self.__props[elem])
        return str
