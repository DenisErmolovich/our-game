import {Question} from '../../_models/question';

export interface QuestionServiceInterface {
  getQuestion(gameId: string, roundId: string, questionId: string): Question;
  updateQuestion(gameId: string, roundId: string, question: Question): void;
}
