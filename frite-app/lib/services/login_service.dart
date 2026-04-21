import 'package:dio/dio.dart';
import 'package:frite/models/login.dart';
import 'package:frite/utils/const.dart';

class LoginService {
  var dio = Dio();

  Future<dynamic> login(Login login) async {
    try {
      print(login.toJson());
      var response = await dio.post("$API/login/", data: login.toJson(), options: 
        Options(
            followRedirects: false,
            validateStatus: (status) {
              return status! < 500;
            }
        )
      );
      print(response);
      return response;
    } on DioError catch (e) {
      if (e.response?.statusCode == 400) {
        return 'Email e/ou senha incorreta!';
      }
      else {
        return 'Erro no sistema. Aguarde um momento.';
      }
    } on Exception catch (e) {
      print(e);
      return null;
    }
  }
}
