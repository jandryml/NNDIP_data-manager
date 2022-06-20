import { LitElement, html, css, customElement } from 'lit-element';
import '@vaadin/number-field/src/vaadin-number-field.js';
import '@vaadin/horizontal-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/checkbox/src/vaadin-checkbox.js';
import '@vaadin/button/src/vaadin-button.js';
import '@vaadin/vertical-layout/src/vaadin-vertical-layout.js';
import '@vaadin/combo-box/src/vaadin-combo-box.js';

@customElement('limit-plan-view')
export class LimitPlanView extends LitElement {
  static get styles() {
    return css`
      :host {
          display: block;
          height: 100%;
      }
      `;
  }

  render() {
    return html`
<vaadin-vertical-layout style="width: 100%; height: 100%; align-items: center; margin: var(--lumo-space-m);">
 <vaadin-button id="saveButton">
   Save 
 </vaadin-button>
 <h2 style="align-self: center;">Temperature config </h2>
 <vaadin-horizontal-layout style="align-self: center; flex-wrap: wrap; align-items: flex-end;" theme="spacing-xl">
  <vaadin-number-field id="optimalTemperature" has-controls type="number" min="0" tabindex="" label="Optimal value"></vaadin-number-field>
  <vaadin-number-field has-controls type="number" min="0" tabindex="" label="Tolerance" id="toleranceTemperature"></vaadin-number-field>
  <vaadin-checkbox style="align-self: flex-end;" tabindex="" label="Enabled" type="checkbox" value="on" id="enabledTemperature"></vaadin-checkbox>
 </vaadin-horizontal-layout>
 <vaadin-horizontal-layout style="margin: var(--lumo-space-s); flex-wrap: wrap;" theme="spacing-xl">
  <vaadin-vertical-layout style="align-self: flex-end;">
   <h4>Min threshold violation event</h4>
   <vaadin-integer-field id="minEventPriority" has-controls type="number" required min="0" placeholder="0-100" value="0" max="100" tabindex="" label="Priority" name="minEventPriority" step="5"></vaadin-integer-field>
   <vaadin-combo-box tabindex="" label="Event" id="minEventTemperature"></vaadin-combo-box>
  </vaadin-vertical-layout>
  <vaadin-vertical-layout style="align-self: flex-end; flex-wrap: wrap;">
   <h4>Max threshold violation event </h4>
   <vaadin-integer-field id="maxEventPriority" has-controls type="number" required min="0" placeholder="0-100" value="0" max="100" tabindex="" label="Priority" name="maxEventPriority" step="5" style="flex-grow: 0;"></vaadin-integer-field>
   <vaadin-combo-box tabindex="" label="Event" id="maxEventTemperature"></vaadin-combo-box>
  </vaadin-vertical-layout>
 </vaadin-horizontal-layout>
 <h2>Co2 config</h2>
 <vaadin-horizontal-layout style="align-self: center; flex-wrap: wrap; justify-content: flex-start;" theme="spacing-xl">
  <vaadin-number-field has-controls type="number" min="0" tabindex="" label="Optimal value" id="optimalCo2"></vaadin-number-field>
  <vaadin-number-field has-controls type="number" min="0" tabindex="" label="Threshold value" id="thresholdCo2"></vaadin-number-field>
  <vaadin-integer-field id="co2Priority" has-controls type="number" required min="0" placeholder="0-100" value="0" max="100" tabindex="" label="Priority" name="co2Priority" step="5"></vaadin-integer-field>
  <vaadin-combo-box tabindex="" label="Event" id="eventCo2"></vaadin-combo-box>
  <vaadin-checkbox style="align-self: flex-end;" tabindex="" label="Enabled" type="checkbox" value="on" id="enabledCo2"></vaadin-checkbox>
 </vaadin-horizontal-layout>
</vaadin-vertical-layout>
`;
  }

  // Remove this method to render the contents of this view inside Shadow DOM
  createRenderRoot() {
    return this;
  }
}
