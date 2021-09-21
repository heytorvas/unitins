import 'package:frite/models/examining.dart';
import 'package:frite/models/psychologist.dart';

class Palographic {
  int? id;
  Examining? examining;
  Psychologist? psychologist;
  String? testDate;
  String? schooling;
  String? test;
  DateTime? createdAt;
  DateTime? updatedAt;

  Palographic(
    {this.id, this.examining, this.psychologist, this.testDate, this.test, this.createdAt, this.updatedAt}
  );

  static fromJson(parsedJson) {
    Palographic p = new Palographic();
    p.id = parsedJson['id'];
    p.examining = Examining.fromJson(parsedJson['examining']);
    p.psychologist = Psychologist.fromJson(parsedJson['psychologist']);
    p.testDate = parsedJson['test_date'];
    p.schooling = parsedJson['schooling'];
    p.test = parsedJson['test'];
    p.createdAt = DateTime.parse(parsedJson['created_at']);
    p.updatedAt = DateTime.parse(parsedJson['updated_at']);
    return p;
  }
}