import {QuestionTypes} from '../_enums/question-types.enum';

export class Question {
  constructor(
    public id: string = null,
    public type: QuestionTypes = null,
    public topic: string = null,
    public text: string = null,
    public answer: string = null,
    public image: Array<string> = new Array<string>(),
    public sound: string = null,
    public answerImage: string = null,
    public isAnswered: boolean = null
  ) {}
}
