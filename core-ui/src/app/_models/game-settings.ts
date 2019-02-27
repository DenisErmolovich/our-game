export class GameSettings {
  constructor(
    public id: string = null,
    public topicsInRound: number = null,
    public questionsInTopic: number = null,
    public superQuestions: number = null,
    public minPrice: number = null,
    public rounds: number = null
  ) {}
}
