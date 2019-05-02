import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IEvaluation, Evaluation } from 'app/shared/model/evaluation.model';
import { EvaluationService } from './evaluation.service';
import { IUser, UserService } from 'app/core';

@Component({
  selector: 'jhi-evaluation-update',
  templateUrl: './evaluation-update.component.html'
})
export class EvaluationUpdateComponent implements OnInit {
  evaluation: IEvaluation;
  isSaving: boolean;

  users: IUser[];

  editForm = this.fb.group({
    id: [],
    speakerId: [],
    title: [],
    orgName: [],
    actor: [],
    speaker: [],
    level: [],
    path: [],
    taskSource: [],
    taskSourceScore: [],
    discoveryAndInnovation: [],
    discoveryAndInnovationScore: [],
    advancedLevel: [],
    advancedLevelScore: [],
    applicationAndPromotion: [],
    applicationAndPromotionScore: [],
    paperScore: [],
    replyScore: [],
    totalScore: [],
    avgScore: [],
    createTime: [],
    updateTime: [],
    evaluator: [],
    remark: [],
    user: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected evaluationService: EvaluationService,
    protected userService: UserService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ evaluation }) => {
      this.updateForm(evaluation);
      this.evaluation = evaluation;
    });
    this.userService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IUser[]>) => mayBeOk.ok),
        map((response: HttpResponse<IUser[]>) => response.body)
      )
      .subscribe((res: IUser[]) => (this.users = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(evaluation: IEvaluation) {
    this.editForm.patchValue({
      id: evaluation.id,
      speakerId: evaluation.speakerId,
      title: evaluation.title,
      orgName: evaluation.orgName,
      actor: evaluation.actor,
      speaker: evaluation.speaker,
      level: evaluation.level,
      path: evaluation.path,
      taskSource: evaluation.taskSource,
      taskSourceScore: evaluation.taskSourceScore,
      discoveryAndInnovation: evaluation.discoveryAndInnovation,
      discoveryAndInnovationScore: evaluation.discoveryAndInnovationScore,
      advancedLevel: evaluation.advancedLevel,
      advancedLevelScore: evaluation.advancedLevelScore,
      applicationAndPromotion: evaluation.applicationAndPromotion,
      applicationAndPromotionScore: evaluation.applicationAndPromotionScore,
      paperScore: evaluation.paperScore,
      replyScore: evaluation.replyScore,
      totalScore: evaluation.totalScore,
      avgScore: evaluation.avgScore,
      createTime: evaluation.createTime != null ? evaluation.createTime.format(DATE_TIME_FORMAT) : null,
      updateTime: evaluation.updateTime != null ? evaluation.updateTime.format(DATE_TIME_FORMAT) : null,
      evaluator: evaluation.evaluator,
      remark: evaluation.remark,
      user: evaluation.user
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const evaluation = this.createFromForm();
    if (evaluation.id !== undefined) {
      this.subscribeToSaveResponse(this.evaluationService.update(evaluation));
    } else {
      this.subscribeToSaveResponse(this.evaluationService.create(evaluation));
    }
  }

  private createFromForm(): IEvaluation {
    const entity = {
      ...new Evaluation(),
      id: this.editForm.get(['id']).value,
      speakerId: this.editForm.get(['speakerId']).value,
      title: this.editForm.get(['title']).value,
      orgName: this.editForm.get(['orgName']).value,
      actor: this.editForm.get(['actor']).value,
      speaker: this.editForm.get(['speaker']).value,
      level: this.editForm.get(['level']).value,
      path: this.editForm.get(['path']).value,
      taskSource: this.editForm.get(['taskSource']).value,
      taskSourceScore: this.editForm.get(['taskSourceScore']).value,
      discoveryAndInnovation: this.editForm.get(['discoveryAndInnovation']).value,
      discoveryAndInnovationScore: this.editForm.get(['discoveryAndInnovationScore']).value,
      advancedLevel: this.editForm.get(['advancedLevel']).value,
      advancedLevelScore: this.editForm.get(['advancedLevelScore']).value,
      applicationAndPromotion: this.editForm.get(['applicationAndPromotion']).value,
      applicationAndPromotionScore: this.editForm.get(['applicationAndPromotionScore']).value,
      paperScore: this.editForm.get(['paperScore']).value,
      replyScore: this.editForm.get(['replyScore']).value,
      totalScore: this.editForm.get(['totalScore']).value,
      avgScore: this.editForm.get(['avgScore']).value,
      createTime:
        this.editForm.get(['createTime']).value != null ? moment(this.editForm.get(['createTime']).value, DATE_TIME_FORMAT) : undefined,
      updateTime:
        this.editForm.get(['updateTime']).value != null ? moment(this.editForm.get(['updateTime']).value, DATE_TIME_FORMAT) : undefined,
      evaluator: this.editForm.get(['evaluator']).value,
      remark: this.editForm.get(['remark']).value,
      user: this.editForm.get(['user']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEvaluation>>) {
    result.subscribe((res: HttpResponse<IEvaluation>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackUserById(index: number, item: IUser) {
    return item.id;
  }
}
