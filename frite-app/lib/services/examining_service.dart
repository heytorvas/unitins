import 'package:dio/dio.dart';
import 'package:frite/main.dart';
import 'package:frite/models/examining.dart';
import 'package:frite/utils/const.dart';

class ExaminingService {
    
  static findAll() async {
    Dio dio = Dio();
    var token = await MyApp.storage.read(key: 'jwt');
    print(token);
    dio.options.headers['Authorization'] = 'Bearer $token';
    var response = await dio.get("$API/examining/");

    List<Examining> lists = [];
    for (var item in response.data) {
      lists.add(Examining.fromJson(item));
    }
    
    return lists;
  }

  static update(int id, Examining examining) async {
    Dio dio = Dio();
    var token = await MyApp.storage.read(key: 'jwt');
    print(token);
    dio.options.headers['Authorization'] = 'Bearer $token';
    print(examining.toJson());
    var response = await dio.put("$API/examining/$id/", data: examining.toJson());
    print(response);
    return response.statusCode;
  }

  static delete(int? id) async {
    Dio dio = Dio();
    var token = await MyApp.storage.read(key: 'jwt');
    print(token);
    dio.options.headers['Authorization'] = 'Bearer $token';
    var response = await dio.delete("$API/examining/$id/");
    print(response);
    return response.statusCode;
  }

  Future<int?> registerExamining(Examining examining) async {
    Dio dio = Dio();
    var token = await MyApp.storage.read(key: 'jwt');
    print(token);
    dio.options.headers['Authorization'] = 'Bearer $token';
    try {
      print(examining.toJson());
      var response =
          await dio.post("$API/examining/", data: examining.toJson());
      print(response);
      if (response.statusCode == 201) {
        return response.statusCode;
      } else {
        return response.statusCode;
      }
    } catch (e) {
      print(e);
      return 0;
    }
  }
  
}