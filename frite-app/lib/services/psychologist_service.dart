import 'package:dio/dio.dart';
import 'package:frite/main.dart';
import 'package:frite/models/psychologist.dart';
import 'package:frite/utils/const.dart';

class PsychologistService {
  
  static findAll() async {
    Dio dio = Dio();
    var token = await MyApp.storage.read(key: 'jwt');
    print(token);
    dio.options.headers['Authorization'] = 'Bearer $token';
    var response = await dio.get("$API/psychologist/");

    List<Psychologist> lists = [];
    for (var item in response.data) {
      lists.add(Psychologist.fromJson(item));
    }
    
    return lists;
  }

  update(int id, Psychologist psychologist) async {
    Dio dio = Dio();
    var token = await MyApp.storage.read(key: 'jwt');
    print(token);
    dio.options.headers['Authorization'] = 'Bearer $token';
    print(psychologist.toJsonPass());
    var response = await dio.patch("$API/psychologist/$id/", data: psychologist.toJsonPass());
    print(response);
    return response.statusCode;
  }

  static delete(int? id) async {
    Dio dio = Dio();
    var token = await MyApp.storage.read(key: 'jwt');
    print(token);
    dio.options.headers['Authorization'] = 'Bearer $token';
    var response = await dio.delete("$API/psychologist/$id/");
    print(response);
    return response.statusCode;
  }

  Future<int?> registerPsychologist(Psychologist psychologist) async {
    Dio dio = Dio();
    try {
      print(psychologist.toJson());
      var response =
          await dio.post("$API/signup/", data: psychologist.toJson());
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
