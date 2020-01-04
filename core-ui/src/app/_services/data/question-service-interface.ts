import {OldQuestion} from '../../_models/old-question';

export interface QuestionServiceInterface {
  getQuestion(gameId: string, roundId: string, questionId: string): OldQuestion;
  updateQuestion(gameId: string, roundId: string, question: OldQuestion): void;
}
