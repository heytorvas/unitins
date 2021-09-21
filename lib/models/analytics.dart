import 'package:frite/models/examining.dart';
import 'package:frite/models/palographic.dart';
import 'package:frite/models/psychologist.dart';

class Analytics {
  int? id;
  Examining? examining;
  Psychologist? psychologist;
  Palographic? palographic;
  int? productivity;
  double? nor;
  String? income;
  double? distanceBetweenPalos;
  double? palosSize;
  int? impulsiveness;
  int? differenceSum;
  DateTime? createdAt;
  DateTime? updatedAt;

  Analytics(
    {this.id, this.examining, this.psychologist, this.palographic, this.productivity, 
    this.nor, this.income, this.distanceBetweenPalos, this.palosSize, this.impulsiveness,
    this.differenceSum, this.createdAt, this.updatedAt}
  );

  static fromJson(parsedJson) {
    Analytics a = new Analytics();
    a.id = parsedJson['id'];
    a.examining = Examining.fromJson(parsedJson['palographic']['examining']);
    a.psychologist = Psychologist.fromJson(parsedJson['palographic']['psychologist']);
    a.palographic = Palographic.fromJson(parsedJson['palographic']);
    a.productivity = parsedJson['productivity'];
    a.nor = parsedJson['nor'];
    a.income = parsedJson['income'];
    a.distanceBetweenPalos = parsedJson['distance_between_palos'];
    a.palosSize = parsedJson['palos_size'];
    a.impulsiveness = parsedJson['impulsiveness'];
    a.differenceSum = parsedJson['difference_sum'];
    a.createdAt = DateTime.parse(parsedJson['created_at']);
    a.updatedAt = DateTime.parse(parsedJson['updated_at']);
    return a;
  }

}
