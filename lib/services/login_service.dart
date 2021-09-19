import 'package:dio/dio.dart';
import 'package:frite/models/login.dart';
import 'package:frite/utils/const.dart';

class LoginService {
  var dio = Dio();

  Future<dynamic> login(Login login) async {
    try {
      print(login.toJson());
      var response = await dio.post("$API/login/", data: login.toJson());
      return response;
    } catch (e) {
      print(e);
      return null;
    }
  }
}
